package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZT5522 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSEQPT LNCSEQPT = new LNCSEQPT();
    SCCGWA SCCGWA;
    LNCT5522_IPT_5522 LN_AWA_5522;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCT5522_IPT_5522 LN_AWA_5522) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LN_AWA_5522 = LN_AWA_5522;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZT5522 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_DISB_PART_INFO_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_DISB_PART_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSEQPT);
        LNCSEQPT.FUNC = 'D';
        LNCSEQPT.CONTRACT_NO = LN_AWA_5522.CONT_NO;
        LNCSEQPT.TRCHCMMT_NO = LN_AWA_5522.COMM_NO;
        LNCSEQPT.VAL_DT = LN_AWA_5522.START_DT;
        LNCSEQPT.TOTAL_AMT = LN_AWA_5522.AMT;
        LNCSEQPT.TOTAL_EQU = LN_AWA_5522.T_C_EQUI;
        LNCSEQPT.CCY = LN_AWA_5522.CCY;
        CEP.TRC(SCCGWA, LN_AWA_5522.CCY);
        CEP.TRC(SCCGWA, LN_AWA_5522.CONT_NO);
        CEP.TRC(SCCGWA, LN_AWA_5522.COMM_NO);
        CEP.TRC(SCCGWA, LN_AWA_5522.START_DT);
        CEP.TRC(SCCGWA, LN_AWA_5522.AMT);
        CEP.TRC(SCCGWA, LN_AWA_5522.T_C_EQUI);
        S000_CALL_LNZSEQPT();
    }
    public void S000_CALL_LNZSEQPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-ENQ-PART", LNCSEQPT);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
