package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4050 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_EOF_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSGLM BPCSGLM = new BPCSGLM();
    SCCGWA SCCGWA;
    BPB4050_AWA_4050 BPB4050_AWA_4050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        CEP.TRC(SCCGWA, "START");
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4050_AWA_4050>");
        BPB4050_AWA_4050 = (BPB4050_AWA_4050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GL_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GL_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_CALL_BPZSGLM();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGLM);
        CEP.TRC(SCCGWA, BPB4050_AWA_4050.MSTNO);
        CEP.TRC(SCCGWA, BPB4050_AWA_4050.CN_TYPE);
        BPCSGLM.DATA.KEY.REDEFINES15.MSTNO = BPB4050_AWA_4050.MSTNO;
        BPCSGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA.KEY.REDEFINES15);
        BPCSGLM.CN_TYPE = BPB4050_AWA_4050.CN_TYPE;
        BPCSGLM.DATA.DATA_TXT.OPT_FLG = 'Y';
        BPCSGLM.INFO.FUNC = 'B';
        CEP.TRC(SCCGWA, BPCSGLM.CN_TYPE);
    }
    public void S000_CALL_BPZSGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-GLM", BPCSGLM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
