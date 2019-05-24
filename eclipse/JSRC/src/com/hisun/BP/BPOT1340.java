package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1340 {
    String JIBS_tmp_str[] = new String[10];
    BPOT1340_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1340_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSDSEQ BPCSDSEQ = new BPCSDSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPC1340 BPC1340;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1340 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPC1340 = new BPC1340();
        IBS.init(SCCGWA, BPC1340);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1340);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B006_DEL_PTR_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B006_DEL_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDSEQ);
        BPCSDSEQ.SEQ_TYPE = BPC1340.SEQ_TYPE;
        BPCSDSEQ.SEQ_CODE = BPC1340.SEQ_NAME;
        S000_CALL_BPZSDSEQ();
    }
    public void S000_CALL_BPZSDSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-DEL-SEQ", BPCSDSEQ);
        if (BPCSDSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSDSEQ.RC);
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
