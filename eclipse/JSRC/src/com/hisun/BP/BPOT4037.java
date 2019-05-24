package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4037 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSITD BPCSITD = new BPCSITD();
    SCCGWA SCCGWA;
    BPB4030_AWA_4030 BPB4030_AWA_4030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4037 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4030_AWA_4030>");
        BPB4030_AWA_4030 = (BPB4030_AWA_4030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        S000_CALL_BPZSITD();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSITD);
        BPCSITD.DATA.KEY.REDEFINES15.BOOK_FLG = BPB4030_AWA_4030.BOOKFLG;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.DATA.KEY.REDEFINES15.CNTR_TYPE = BPB4030_AWA_4030.PRODMOD;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.INFO.FUNC = BPB4030_AWA_4030.FUNC;
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.BOOKFLG);
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.PRODMOD);
    }
    public void S000_CALL_BPZSITD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-ITD", BPCSITD);
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
