package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZSQSEQ_WS_MSGID WS_MSGID = new BPZSQSEQ_WS_MSGID();
    BPCX01 BPCX01 = new BPCX01();
    BPCX03 BPCX03 = new BPCX03();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSQSEQ BPCSQSEQ;
    public void MP(SCCGWA SCCGWA, BPCSQSEQ BPCSQSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQSEQ = BPCSQSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSQSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_CODE();
        if (pgmRtn) return;
        B002_QRY_PTR_PROC();
        if (pgmRtn) return;
        if (BPCSQSEQ.FUNC == 'O') {
            B014_OUTPUT_FOR_PRINT();
            if (pgmRtn) return;
        } else {
            B015_OUTPUT_FOR_SUB_TX();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = BPCSQSEQ.SEQ_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCSQSEQ.SEQ_CODE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
    }
    public void B002_QRY_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPCSQSEQ.SEQ_TYPE;
        BPCUQSEQ.SEQ_CODE = BPCSQSEQ.SEQ_CODE;
        S000_CALL_BPZUQSEQ();
        if (pgmRtn) return;
    }
    public void B014_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX01);
        IBS.init(SCCGWA, SCCFMT);
        BPCX01.TYPE = BPCUQSEQ.SEQ_TYPE;
        BPCX01.NAME = BPCUQSEQ.SEQ_CODE;
        BPCX01.DESC = BPCUQSEQ.SEQ_DESC;
        BPCX01.SEQ_NO = BPCUQSEQ.SEQ_NO;
        BPCX01.FIRST_NUM = BPCUQSEQ.FIRST_NUM;
        BPCX01.INIT_ZERO = BPCUQSEQ.INIT_ZERO;
        BPCX01.SKIP_FLG = BPCUQSEQ.SKIP_FLG;
        BPCX01.OBL_FLG = BPCUQSEQ.OBL_FLG;
        BPCX01.MAX_SEQ_NO = BPCUQSEQ.MAX_SEQ_NO;
        BPCX01.MAX_FLG = BPCUQSEQ.MAX_FLG;
        BPCX01.WARN_SEQ_NO = BPCUQSEQ.WARN_SEQ_NO;
        BPCX01.LAST_UPD_TLR = BPCUQSEQ.LAST_UPD_TLR;
        BPCX01.LAST_UPD_DATE = BPCUQSEQ.LAST_UPD_DATE;
        BPCX01.STEP_NUM = (short) BPCUQSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCUQSEQ.STEP_NUM);
        CEP.TRC(SCCGWA, BPCX01.STEP_NUM);
        SCCFMT.FMTID = "BPO02";
        SCCFMT.DATA_PTR = BPCX01;
        SCCFMT.DATA_LEN = 220;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B015_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX03);
        IBS.init(SCCGWA, SCCFMT);
        BPCX03.TYPE = BPCUQSEQ.SEQ_TYPE;
        BPCX03.NAME = BPCUQSEQ.SEQ_CODE;
        BPCX03.DESC = BPCUQSEQ.SEQ_DESC;
        BPCX03.FIRST_NUM = BPCUQSEQ.FIRST_NUM;
        BPCX03.INIT_ZERO = BPCUQSEQ.INIT_ZERO;
        BPCX03.SKIP_FLG = BPCUQSEQ.SKIP_FLG;
        BPCX03.OBL_FLG = BPCUQSEQ.OBL_FLG;
        BPCX03.MAX_SEQ_NO = BPCUQSEQ.MAX_SEQ_NO;
        BPCX03.MAX_FLG = BPCUQSEQ.MAX_FLG;
        BPCX03.WARN_SEQ_NO = BPCUQSEQ.WARN_SEQ_NO;
        BPCX03.TYPE_NAME = BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_NAME;
        BPCX03.CODE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCX03.STEP_NUM = (short) BPCUQSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCX03.STEP_NUM);
        CEP.TRC(SCCGWA, BPCUQSEQ.STEP_NUM);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCX03;
        SCCFMT.DATA_LEN = 431;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSQSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSQSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
