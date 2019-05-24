package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4031 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
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
        CEP.TRC(SCCGWA, "BPOT4031 return!");
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
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.BOOKFLG);
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.PRODMOD);
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.ITMS[1-1].ITM_RMK);
        CEP.TRC(SCCGWA, BPB4030_AWA_4030.ITMS[2-1].ITM_RMK);
        IBS.init(SCCGWA, BPCSITD);
        BPCSITD.DATA.KEY.REDEFINES15.BOOK_FLG = BPB4030_AWA_4030.BOOKFLG;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.DATA.KEY.REDEFINES15.CNTR_TYPE = BPB4030_AWA_4030.PRODMOD;
        BPCSITD.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSITD.DATA.KEY.REDEFINES15);
        BPCSITD.DATA.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSITD.DATA.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCSITD.DATA.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCSITD.DATA.DATA_TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCSITD.DATA.DATA_TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        for (WS_I = 1; WS_I <= 32; WS_I += 1) {
            BPCSITD.DATA.DATA_TXT.REL_ITMS[WS_I-1].REMARK = BPB4030_AWA_4030.ITMS[WS_I-1].ITM_RMK;
        }
        BPCSITD.INFO.FUNC = 'A';
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
