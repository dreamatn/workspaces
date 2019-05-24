package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3305 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_S_BRW_BR_HIS = "BP-S-BRW-BR-HIS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSQBRH BPCSQBRH = new BPCSQBRH();
    SCCGWA SCCGWA;
    BPB3305_AWA_3305 BPB3305_AWA_3305;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3305 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3305_AWA_3305>");
        BPB3305_AWA_3305 = (BPB3305_AWA_3305) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3305_AWA_3305.ACTC);
        if (BPB3305_AWA_3305.ACTC == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB3305_AWA_3305.ACTC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3305_AWA_3305.ACTC != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3305_AWA_3305.ACTC;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3305_AWA_3305.ACTC_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3305_AWA_3305.START_DT > BPB3305_AWA_3305.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE;
            WS_FLD_NO = BPB3305_AWA_3305.START_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQBRH);
        BPCSQBRH.DATA.BR = BPB3305_AWA_3305.ACTC;
        BPCSQBRH.DATA.START_DT = BPB3305_AWA_3305.START_DT;
        BPCSQBRH.DATA.END_DT = BPB3305_AWA_3305.END_DT;
        BPCSQBRH.FUNC = 'Q';
        S000_CALL_BPZSQBRH();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSQBRH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_BR_HIS, BPCSQBRH);
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
