package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9203 {
    String K_CPN_S_BROWSE_UPDATA = "BP-S-BROWSE-UPDATA  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBUPD BPCSBUPD = new BPCSBUPD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9203_AWA_9203 BPB9203_AWA_9203;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9203 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9203_AWA_9203>");
        BPB9203_AWA_9203 = (BPB9203_AWA_9203) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBUPD);
        BPCSBUPD.FUNC = 'B';
        BPCSBUPD.BATCH_NO = BPB9203_AWA_9203.BAT_NO;
        BPCSBUPD.UP_TYPE = BPB9203_AWA_9203.UP_TYPE;
        CEP.TRC(SCCGWA, BPB9203_AWA_9203.BAT_NO);
        CEP.TRC(SCCGWA, BPB9203_AWA_9203.UP_TYPE);
        S010_CALL_BPZSBUPD();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSBUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_BROWSE_UPDATA, BPCSBUPD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
