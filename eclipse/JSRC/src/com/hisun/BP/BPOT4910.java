package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4910 {
    String CPN_TLR_STS_MAINTAIN = "BP-S-TLR-STS-M      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_OPT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSM BPCSTLSM = new BPCSTLSM();
    SCCGWA SCCGWA;
    BPB4910_AWA_4910 BPB4910_AWA_4910;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4910 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4910_AWA_4910>");
        BPB4910_AWA_4910 = (BPB4910_AWA_4910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
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
        if ((WS_OPT != 'T' 
            && WS_OPT != 'S' 
            && WS_OPT != 'R' 
            && WS_OPT != 'N' 
            && WS_OPT != 'L')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4910_AWA_4910.OPT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_STATUS_MAINTAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSM);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            R000_TRANS_DATA_PARAMETER_CN();
        } else {
            R000_TRANS_DATA_PARAMETER();
        }
        S000_CALL_BPZSTLSM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSM.OPT = BPB4910_AWA_4910.OPT;
        BPCSTLSM.TLR = BPB4910_AWA_4910.TLR;
        if (BPB4910_AWA_4910.OPT == 'I') {
            BPCSTLSM.IN_BR = BPB4910_AWA_4910.TLR_BR;
            BPCSTLSM.IN_BR_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else if (BPB4910_AWA_4910.OPT == 'O') {
            BPCSTLSM.OUT_BR = BPB4910_AWA_4910.TLR_BR;
            BPCSTLSM.OUT_BR_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CEP.TRC(SCCGWA, "AAAAAA");
            BPCSTLSM.TLR_BR = BPB4910_AWA_4910.TLR_BR;
        }
    }
    public void R000_TRANS_DATA_PARAMETER_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4910_AWA_4910.OPT);
        CEP.TRC(SCCGWA, BPB4910_AWA_4910.TLR);
        CEP.TRC(SCCGWA, BPB4910_AWA_4910.TLR_BR);
        CEP.TRC(SCCGWA, BPB4910_AWA_4910.OUT_BR);
        CEP.TRC(SCCGWA, BPB4910_AWA_4910.IN_BR);
        BPCSTLSM.OPT = BPB4910_AWA_4910.OPT;
        BPCSTLSM.TLR = BPB4910_AWA_4910.TLR;
        BPCSTLSM.TLR_NM = BPB4910_AWA_4910.TLR_NM;
        BPCSTLSM.STS = BPB4910_AWA_4910.TLR_STS;
        BPCSTLSM.CHK_FLG = BPB4910_AWA_4910.CHK_FLG;
        if (BPB4910_AWA_4910.OPT == 'T') {
            CEP.TRC(SCCGWA, "MOVE TLR");
            BPCSTLSM.OUT_BR = BPB4910_AWA_4910.OUT_BR;
            BPCSTLSM.IN_BR = BPB4910_AWA_4910.IN_BR;
            BPCSTLSM.IN_BR_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSTLSM.OUT_BR_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CEP.TRC(SCCGWA, "AAAAAA");
            BPCSTLSM.TLR_BR = BPB4910_AWA_4910.TLR_BR;
        }
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
