package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1345 {
    String JIBS_tmp_str[] = new String[10];
    BPOT1345_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1345_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSBSEQ BPCSBSEQ = new BPCSBSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1345_AWA_1345 BPB1345_AWA_1345;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1345 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1345_AWA_1345>");
        BPB1345_AWA_1345 = (BPB1345_AWA_1345) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1345_AWA_1345.SEQ_TYPE);
        CEP.TRC(SCCGWA, "RDZCC");
        CEP.TRC(SCCGWA, BPB1345_AWA_1345.SEQ_NAME);
        B001_CHECK_INPUT();
        B007_BRW_PTR_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B007_BRW_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBSEQ);
        BPCSBSEQ.SEQ_TYPE = BPB1345_AWA_1345.SEQ_TYPE;
        BPCSBSEQ.SEQ_CODE = BPB1345_AWA_1345.SEQ_NAME;
        S000_CALL_BPZSBSEQ();
    }
    public void S000_CALL_BPZSBSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BRW-SEQ", BPCSBSEQ);
        if (BPCSBSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSBSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
