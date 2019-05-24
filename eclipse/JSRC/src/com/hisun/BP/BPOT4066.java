package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4066 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSCNGL BPCSCNGL = new BPCSCNGL();
    SCCGWA SCCGWA;
    BPB4060_AWA_4060 BPB4060_AWA_4060;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4066 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4060_AWA_4060>");
        BPB4060_AWA_4060 = (BPB4060_AWA_4060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA();
        S000_CALL_BPZSCNGL();
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCNGL);
        BPCSCNGL.DAT.KEY.CNTR_TYPE = BPB4060_AWA_4060.CNTR_TYP;
        BPCSCNGL.DAT.KEY.BOOK_FLG = BPB4060_AWA_4060.BOOK_FLG;
        BPCSCNGL.DAT.KEY.BR = BPB4060_AWA_4060.BR;
        BPCSCNGL.DAT.KEY.OTH = BPB4060_AWA_4060.OTH;
        CEP.TRC(SCCGWA, BPB4060_AWA_4060.CNTR_TYP);
        CEP.TRC(SCCGWA, BPB4060_AWA_4060.BOOK_FLG);
        CEP.TRC(SCCGWA, BPB4060_AWA_4060.BR);
        CEP.TRC(SCCGWA, BPB4060_AWA_4060.OTH);
        BPCSCNGL.INFO.FUNC = 'I';
    }
    public void S000_CALL_BPZSCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-CNGL", BPCSCNGL);
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
