package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8623 {
    int JIBS_tmp_int;
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
    LNB2110_AWA_2110 LNB2110_AWA_2110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8623 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2110_AWA_2110>");
        LNB2110_AWA_2110 = (LNB2110_AWA_2110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_BRW_PART_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_BRW_PART_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSEQPT);
        LNCSEQPT.FUNC = 'B';
        LNCSEQPT.DISB_REF = "" + LNB2110_AWA_2110.DISB_REF;
        JIBS_tmp_int = LNCSEQPT.DISB_REF.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) LNCSEQPT.DISB_REF = "0" + LNCSEQPT.DISB_REF;
        LNCSEQPT.TRCHCMMT_NO = LNB2110_AWA_2110.COMM_NO;
        LNCSEQPT.ALL_IN_RATE = LNB2110_AWA_2110.IN_RATE;
        LNCSEQPT.FLAG = LNB2110_AWA_2110.CON_FLAG;
        CEP.TRC(SCCGWA, LNB2110_AWA_2110.DISB_REF);
        CEP.TRC(SCCGWA, LNB2110_AWA_2110.COMM_NO);
        CEP.TRC(SCCGWA, LNB2110_AWA_2110.IN_RATE);
        CEP.TRC(SCCGWA, LNCSEQPT.FLAG);
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
