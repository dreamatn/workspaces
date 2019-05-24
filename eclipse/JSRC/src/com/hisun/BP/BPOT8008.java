package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8008 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    BPOT8008_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8008_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCTQFLT BPCTQFLT = new BPCTQFLT();
    SCCGWA SCCGWA;
    BPB8000_AWA_8000 BPB8000_AWA_8000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT8008 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8000_AWA_8000>");
        BPB8000_AWA_8000 = (BPB8000_AWA_8000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READ_PROCESS();
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTQFLT);
        BPCTQFLT.FUNC = BPB8000_AWA_8000.FUNC;
        BPCTQFLT.TYP = BPB8000_AWA_8000.PARM_TYP;
        BPCTQFLT.FLT_CD = BPB8000_AWA_8000.FLT_CODE;
        CEP.TRC(SCCGWA, BPB8000_AWA_8000.FLT_CODE);
        S000_CALL_BPZTQFLT();
    }
    public void S000_CALL_BPZTQFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQ-FLT-ITEM-TYPH", BPCTQFLT);
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
