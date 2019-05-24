package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1141 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT1 = "BPX59";
    String K_OUTPUT_FMT3 = "BPX01";
    String CPN_FCRE_MAINTAIN = "BP-F-S-MAINTAIN-FCRE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCRE BPCOFCRE = new BPCOFCRE();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB1141_AWA_1141 BPB1141_AWA_1141;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1141 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1141_AWA_1141>");
        BPB1141_AWA_1141 = (BPB1141_AWA_1141) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_REB_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_REB_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCRE);
        CEP.TRC(SCCGWA, BPB1141_AWA_1141.REB_CODE);
        R000_TRANS_DATA_PARAMETER();
        BPCOFCRE.FUNC = 'I';
        BPCOFCRE.OUTPUT_FMT = K_OUTPUT_FMT3;
        S000_CALL_BPZFCREB();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFCRE.DATA_INFO.REB_CODE = BPB1141_AWA_1141.REB_CODE;
        CEP.TRC(SCCGWA, BPCOFCRE.DATA_INFO.REB_CODE);
    }
    public void S000_CALL_BPZFCREB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FCRE_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFCRE;
        SCCCALL.ERR_FLDNO = BPB1141_AWA_1141.REB_CODE;
        IBS.CALL(SCCGWA, SCCCALL);
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
