package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9205 {
    String K_CPN_BP_S_UPD_UPDATA = "BP-S-UPD-UPDATA     ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSUUPD BPCSUUPD = new BPCSUUPD();
    SCCGWA SCCGWA;
    BPB9205_AWA_9205 BPB9205_AWA_9205;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9205 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9205_AWA_9205>");
        BPB9205_AWA_9205 = (BPB9205_AWA_9205) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TUPF_INFO_UPDATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TUPF_INFO_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSUUPD);
        BPCSUUPD.FUNC = 'U';
        BPCSUUPD.KEY.BATCH_NO = BPB9205_AWA_9205.BAT_NO;
        BPCSUUPD.KEY.SEQ = BPB9205_AWA_9205.SEQ_NO;
        BPCSUUPD.TYPE = BPB9205_AWA_9205.TYPE;
        BPCSUUPD.EX_DATA = BPB9205_AWA_9205.EX_DATA;
        CEP.TRC(SCCGWA, BPB9205_AWA_9205.BAT_NO);
        CEP.TRC(SCCGWA, BPB9205_AWA_9205.SEQ_NO);
        CEP.TRC(SCCGWA, BPB9205_AWA_9205.TYPE);
        CEP.TRC(SCCGWA, BPB9205_AWA_9205.EX_DATA);
        S010_CALL_BPZSUUPD();
    }
    public void S010_CALL_BPZSUUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_BP_S_UPD_UPDATA, BPCSUUPD);
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
