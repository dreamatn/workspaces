package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3303 {
    String K_CMP_TXH_INQ = "BP-S-INQ-TX-HIS";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSQTXH BPCSQTXH = new BPCSQTXH();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB3300_AWA_3300 BPB3300_AWA_3300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3303 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3300_AWA_3300>");
        BPB3300_AWA_3300 = (BPB3300_AWA_3300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3300_AWA_3300.JNO == 0 
            || BPB3300_AWA_3300.JNO == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_JNO;
            WS_FLD_NO = BPB3300_AWA_3300.JNO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB3300_AWA_3300.AC_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            WS_FLD_NO = BPB3300_AWA_3300.AC_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQTXH);
        BPCSQTXH.DATA.JNO = BPB3300_AWA_3300.JNO;
        BPCSQTXH.DATA.AC_DT = BPB3300_AWA_3300.AC_DT;
        BPCSQTXH.FUNC = 'Q';
        BPCSQTXH.OUTPUT_FLG = 'S';
        S000_CALL_BPZSQTXH();
    }
    public void S000_CALL_BPZSQTXH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXH_INQ, BPCSQTXH);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
