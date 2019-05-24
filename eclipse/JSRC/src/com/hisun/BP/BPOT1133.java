package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1133 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP057";
    String CPN_FAMO_MAINTAIN = "BP-F-S-MAINTAIN-FAMO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    BPOT1133_WS_MMDD WS_MMDD = new BPOT1133_WS_MMDD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFAMO BPCOFAMO = new BPCOFAMO();
    SCCGWA SCCGWA;
    BPB1127_AWA_1127 BPB1127_AWA_1127;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1133 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1127_AWA_1127>");
        BPB1127_AWA_1127 = (BPB1127_AWA_1127) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_BROWSE_AMO_RATE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1127_AWA_1127.AMORT_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FAMO_AMO_CODE_NOTINP;
            WS_FLD_NO = BPB1127_AWA_1127.AMORT_CD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_AMO_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFAMO);
        BPCOFAMO.FUNC = 'B';
        if (BPB1127_AWA_1127.AMORT_CD.trim().length() > 0) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
        }
        BPCOFAMO.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZFSAMO();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFAMO.KEY.AMORT_CODE = BPB1127_AWA_1127.AMORT_CD;
    }
    public void S000_CALL_BPZFSAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_FAMO_MAINTAIN, BPCOFAMO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
