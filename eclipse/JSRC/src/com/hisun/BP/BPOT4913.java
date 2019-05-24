package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4913 {
    String CPN_TLR_STS_MAINTAIN = "BP-S-TLR-STS-M      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_OPT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSM BPCSTLSM = new BPCSTLSM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB4910_AWA_4910 BPB4910_AWA_4910;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4913 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4910_AWA_4910>");
        BPB4910_AWA_4910 = (BPB4910_AWA_4910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSTLSM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TLR_STATUS_MAINTAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        if (BPB4910_AWA_4910.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_MUST_INPUT;
            WS_FLD_NO = BPB4910_AWA_4910.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        WS_OPT = BPB4910_AWA_4910.OPT;
        if ((WS_OPT != 'Q' 
            && WS_OPT != 'H' 
            && WS_OPT != 'C' 
            && WS_OPT != 'M' 
            && WS_OPT != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4910_AWA_4910.OPT_NO;
            S000_ERR_MSG_PROC();
        }
        BPCSTLSM.OPT = BPB4910_AWA_4910.OPT;
        if (BPCSTLSM.OPT == 'H') {
            if (BPB4910_AWA_4910.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_TOO_SMALL;
                WS_FLD_NO = BPB4910_AWA_4910.BEGIN_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCSTLSM.OPT == 'M') {
            if (BPB4910_AWA_4910.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FURLOUGH_ERR;
                WS_FLD_NO = BPB4910_AWA_4910.BEGIN_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCSTLSM.OPT == 'A') {
            if (BPB4910_AWA_4910.END_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AHEAD_FURLOUGH_ERR;
                WS_FLD_NO = BPB4910_AWA_4910.END_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCSTLSM.OPT == 'C') {
            if (BPB4910_AWA_4910.BEGIN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FURLOUGH_ERR;
                WS_FLD_NO = BPB4910_AWA_4910.BEGIN_DT_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPB4910_AWA_4910.END_DT < BPB4910_AWA_4910.BEGIN_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
            WS_FLD_NO = BPB4910_AWA_4910.END_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_STATUS_MAINTAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSM);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSM.OPT = BPB4910_AWA_4910.OPT;
        BPCSTLSM.TLR_BR = BPB4910_AWA_4910.TLR_BR;
        BPCSTLSM.TLR = BPB4910_AWA_4910.TLR;
        BPCSTLSM.BEGIN_DT = BPB4910_AWA_4910.BEGIN_DT;
        BPCSTLSM.BEGIN_TIME = BPB4910_AWA_4910.BEGIN_TM;
        BPCSTLSM.END_DT = BPB4910_AWA_4910.END_DT;
        BPCSTLSM.END_TIME = BPB4910_AWA_4910.END_TM;
        BPCSTLSM.SIGN_FLG = BPB4910_AWA_4910.SIGN_FLG;
        BPCSTLSM.CHK_FLG = BPB4910_AWA_4910.CHK_FLG;
    }
    public void S000_CALL_BPZSTLSM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_STS_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSTLSM;
        SCCCALL.ERR_FLDNO = BPB4910_AWA_4910.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}