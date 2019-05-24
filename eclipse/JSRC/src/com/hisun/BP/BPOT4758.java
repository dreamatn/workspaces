package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4758 {
    String JIBS_tmp_str[] = new String[10];
    String K_CALL_BPZQHDYI = "BP-P-INQ-DATE-INFO ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_FMT_CD = "BP455";
    BPOT4758_WS_ERR_MSG WS_ERR_MSG = new BPOT4758_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQHDYI BPCQHDYI = new BPCQHDYI();
    SCCGWA SCCGWA;
    BPB4758_AWA_4758 BPB4758_AWA_4758;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4758 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4758_AWA_4758>");
        BPB4758_AWA_4758 = (BPB4758_AWA_4758) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
        B200_SET_RETURN_INFO();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQHDYI);
        BPCQHDYI.INPUT_DAT.CAL_CODE = BPB4758_AWA_4758.CAL_CD;
        BPCQHDYI.INPUT_DAT.DATE = BPB4758_AWA_4758.DATE;
        S000_CALL_BPZQHDYI();
    }
    public void B200_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = BPCQHDYI.OUTPUT_DAT;
        SCCFMT.DATA_LEN = 124;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZQHDYI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQHDYI, BPCQHDYI);
        if (BPCQHDYI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQHDYI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
