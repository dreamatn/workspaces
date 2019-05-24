package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9201 {
    String K_S_LOAD_UPDATA = "BP-S-LOAD-UPDATA";
    String K_BPPUPF = "BPPUPF        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOEHOL BPCOEHOL = new BPCOEHOL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSLUPD BPCSLUPD = new BPCSLUPD();
    BPCOXMSG BPCOXMSG = new BPCOXMSG();
    SCCGWA SCCGWA;
    BPB9201_AWA_9201 BPB9201_AWA_9201;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9201 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9201_AWA_9201>");
        BPB9201_AWA_9201 = (BPB9201_AWA_9201) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSLUPD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_PROCESS();
        B200_MAIN_PROCESS();
        R000_OUTPUT_PROC();
        B200_SET_SUB_TRN();
    }
    public void B100_CHECK_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_TYPE);
        if (BPB9201_AWA_9201.BATCH_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY;
            WS_FLD_NO = BPB9201_AWA_9201.BATCH_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLUPD);
        BPCSLUPD.PROC_NAME = K_BPPUPF;
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.BATCH_NO);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_TYPE);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_TERM);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_FILE);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.IP_ADDR);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.FIL_PATH);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_TLR);
        CEP.TRC(SCCGWA, BPB9201_AWA_9201.UP_BR);
        BPCSLUPD.PARM_DATA.BATCH_NO = BPB9201_AWA_9201.BATCH_NO;
        BPCSLUPD.PARM_DATA.UP_TYPE = BPB9201_AWA_9201.UP_TYPE;
        BPCSLUPD.PARM_DATA.FILE_NAME.UP_TRM = BPB9201_AWA_9201.UP_TERM;
        BPCSLUPD.PARM_DATA.FILE_NAME.FIL_NAM = BPB9201_AWA_9201.UP_FILE;
        BPCSLUPD.PARM_DATA.IP_ADDR = BPB9201_AWA_9201.IP_ADDR;
        BPCSLUPD.PARM_DATA.FIL_PATH = BPB9201_AWA_9201.FIL_PATH;
        BPCSLUPD.PARM_DATA.TLR_NO = BPB9201_AWA_9201.UP_TLR;
        BPCSLUPD.PARM_DATA.BR_NO = BPB9201_AWA_9201.UP_BR;
        CEP.TRC(SCCGWA, BPCSLUPD);
        S000_CALL_BPZSLUPD();
    }
    public void R000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        BPCOXMSG.BATCH_NO = BPB9201_AWA_9201.BATCH_NO;
        BPCOXMSG.TYPE = BPB9201_AWA_9201.UP_TYPE;
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCOXMSG;
        SCCFMT.DATA_LEN = 38;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B200_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 9267;
        S000_SET_SUBS_TRN();
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_BPZSLUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_LOAD_UPDATA, BPCSLUPD);
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
