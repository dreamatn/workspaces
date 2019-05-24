package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4105 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP242";
    String CPN_ASMT_MAINTAIN = "BP-S-MAINT-ASMT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSASMT BPCSASMT = new BPCSASMT();
    BPRBKPM BPRBKPM = new BPRBKPM();
    SCCGWA SCCGWA;
    BPB4100_AWA_4100 BPB4100_AWA_4100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4105 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4100_AWA_4100>");
        BPB4100_AWA_4100 = (BPB4100_AWA_4100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        S000_UPD_PROC_CONTINUE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_RESULT_PROC();
    }
    public void S000_UPD_PROC_CONTINUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSASMT);
        BPCSASMT.DAT.DAT_TXT.BOOK_TYP = BPB4100_AWA_4100.BOOK_TYP;
        BPCSASMT.DAT.KEY.BOOK_FLG = BPB4100_AWA_4100.BOOK_FLG;
        BPCSASMT.INFO.FUNC = 'I';
        S000_CALL_BPZSASMT();
    }
    public void S000_CALL_BPZSASMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ASMT_MAINTAIN, BPCSASMT);
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
