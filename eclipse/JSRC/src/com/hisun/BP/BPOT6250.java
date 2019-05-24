package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6250 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_MAINTAIN_RNUM = "BP-S-MAINTAIN-RNUM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSRNUM BPCSRNUM = new BPCSRNUM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6250_AWA_6250 BPB6250_AWA_6250;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6250 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6250_AWA_6250>");
        BPB6250_AWA_6250 = (BPB6250_AWA_6250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSRNUM);
        BPCSRNUM.FUNC = 'B';
        BPCSRNUM.BR = BPB6250_AWA_6250.BR;
        BPCSRNUM.CCY = BPB6250_AWA_6250.CCY;
        BPCSRNUM.CRNUM = BPB6250_AWA_6250.CRNUM;
        S000_CALL_BPZSRNUM();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6250_AWA_6250.BR);
        CEP.TRC(SCCGWA, BPB6250_AWA_6250.CCY);
        CEP.TRC(SCCGWA, BPB6250_AWA_6250.CRNUM);
    }
    public void S000_CALL_BPZSRNUM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINTAIN_RNUM, BPCSRNUM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSRNUM.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
