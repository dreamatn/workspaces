package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4057 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
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
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4057 return!");
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
        if (BPB4050_AWA_4050.EXP_DATE <= BPB4050_AWA_4050.EFF_DATE) {
            WS_MSG_ERR = "BP1561";
            WS_FLD_NO = BPB4050_AWA_4050.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GL_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_CALL_BPZSGLM();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGLM);
        BPCSGLM.DATA.KEY.REDEFINES15.MSTNO = BPB4050_AWA_4050.MSTNO;
        BPCSGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSGLM.DATA.KEY.REDEFINES15);
        BPCSGLM.DATA.P_EFF_DATE = BPB4050_AWA_4050.P_EFF_DT;
        CEP.TRC(SCCGWA, BPB4050_AWA_4050.MSTNO);
        CEP.TRC(SCCGWA, BPB4050_AWA_4050.P_EFF_DT);
        BPCSGLM.INFO.FUNC = 'I';
    }
    public void S000_CALL_BPZSGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-GLM", BPCSGLM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
