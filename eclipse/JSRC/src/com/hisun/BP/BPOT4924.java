package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4924 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_TDTL_MT = "BP-R-TLR-HOL-M      ";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTDTL BPRTDTL = new BPRTDTL();
    BPRTLT BPRTLT = new BPRTLT();
    BPCRTLTS BPCRTLTS = new BPCRTLTS();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    SCCGWA SCCGWA;
    BPB4924_AWA_4924 BPB4924_AWA_4924;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4924 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4924_AWA_4924>");
        BPB4924_AWA_4924 = (BPB4924_AWA_4924) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TLR_STATUS_MAINTAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4924_AWA_4924.TLR);
        CEP.TRC(SCCGWA, BPB4924_AWA_4924.BEGIN_DT);
        CEP.TRC(SCCGWA, BPB4924_AWA_4924.END_DT);
        CEP.TRC(SCCGWA, BPB4924_AWA_4924.BEGIN_TM);
        CEP.TRC(SCCGWA, BPB4924_AWA_4924.END_TM);
        if (BPB4924_AWA_4924.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_MUST_INPUT;
            WS_FLD_NO = BPB4924_AWA_4924.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4924_AWA_4924.END_DT < BPB4924_AWA_4924.BEGIN_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TIME_RANGE_ERR;
            WS_FLD_NO = BPB4924_AWA_4924.END_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TLR_STATUS_MAINTAIN() throws IOException,SQLException,Exception {
        B030_TLR_STATUS();
        BPRTDTL.KEY.TLR = BPB4924_AWA_4924.TLR;
        BPCRTLTS.INFO.FUNC = 'R';
        BPRTDTL.KEY.TYPE = 'A';
        S000_CALL_BPZRTLTS();
        BPRTDTL.KEY.TLR = BPB4924_AWA_4924.TLR;
        BPRTDTL.BEGIN_DT = BPB4924_AWA_4924.BEGIN_DT;
        BPRTDTL.END_DT = BPB4924_AWA_4924.END_DT;
        BPRTDTL.BEGIN_TIME = BPB4924_AWA_4924.BEGIN_TM;
        BPRTDTL.END_TIME = BPB4924_AWA_4924.END_TM;
        BPRTDTL.KEY.TYPE = 'A';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            BPCRTLTS.INFO.FUNC = 'C';
        } else {
            BPCRTLTS.INFO.FUNC = 'M';
        }
        S000_CALL_BPZRTLTS();
    }
    public void B030_TLR_STATUS() throws IOException,SQLException,Exception {
        if (BPB4924_AWA_4924.BEGIN_DT == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPRTLT);
            BPCRTLTM.INFO.FUNC = 'R';
            BPRTLT.KEY.TLR = BPB4924_AWA_4924.TLR;
            S000_CALL_BPZRTLTM();
            CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
            BPRTLT.TLR_STS = 'S';
            BPCRTLTM.INFO.FUNC = 'M';
            CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
            S000_CALL_BPZRTLTM();
            CEP.TRC(SCCGWA, BPRTLT.TLR_STS);
        }
    }
    public void S000_CALL_BPZRTLTS() throws IOException,SQLException,Exception {
        BPCRTLTS.INFO.POINTER = BPRTDTL;
        BPCRTLTS.INFO.LEN = 93;
        IBS.CALLCPN(SCCGWA, CPN_TDTL_MT, BPCRTLTS);
        CEP.TRC(SCCGWA, BPCRTLTS.RC.RC_CODE);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
        if (BPCRTLTM.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLTM() throws IOException,SQLException,Exception {
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
        }
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
