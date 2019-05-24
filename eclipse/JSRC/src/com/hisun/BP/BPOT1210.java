package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1210 {
    String CPN_S_BPZFSSAF = "BP-S-BRW-BPZFSSAF";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFSSAF BPCFSSAF = new BPCFSSAF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1210_AWA_1210 BPB1210_AWA_1210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1210 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1210_AWA_1210>");
        BPB1210_AWA_1210 = (BPB1210_AWA_1210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSSAF);
        BPCFSSAF.INF.START_DATE = BPB1210_AWA_1210.ST_DT;
        BPCFSSAF.INF.END_DATE = BPB1210_AWA_1210.EN_DT;
        BPCFSSAF.INF.CI_NO = BPB1210_AWA_1210.CI_NO;
        BPCFSSAF.INF.FEE_CODE = BPB1210_AWA_1210.FEE_CD;
        S00_CALL_BPZFSSAF();
    }
    public void S00_CALL_BPZFSSAF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFSSAF, BPCFSSAF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}