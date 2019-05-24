package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4244 {
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMACD BPCSMACD = new BPCSMACD();
    SCCGWA SCCGWA;
    BPB4240_AWA_4240 BPB4240_AWA_4240;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4244 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4240_AWA_4240>");
        BPB4240_AWA_4240 = (BPB4240_AWA_4240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSMACD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMACD);
        BPCSMACD.TXN_CD = BPB4240_AWA_4240.TXN_CODE;
        BPCSMACD.SEQ_NO = BPB4240_AWA_4240.SEQNO;
        BPCSMACD.PARM_DATA.DISPLAY_SEQ = BPB4240_AWA_4240.DISSEQ;
        BPCSMACD.PARM_DATA.FLD_DESCE = BPB4240_AWA_4240.DESCE_30;
        BPCSMACD.PARM_DATA.FLD_DESC = BPB4240_AWA_4240.DESC;
        BPCSMACD.PARM_DATA.FLD_TYPE = BPB4240_AWA_4240.TYPE;
        BPCSMACD.PARM_DATA.FLD_LEN = BPB4240_AWA_4240.LEN;
        BPCSMACD.PARM_DATA.DEC_LEN = BPB4240_AWA_4240.DEC_LEN;
        BPCSMACD.PARM_DATA.SG_FLG = BPB4240_AWA_4240.SG_FLG;
        BPCSMACD.PARM_DATA.FLD_IPT_FLG = BPB4240_AWA_4240.IPT_FLG;
        BPCSMACD.PARM_DATA.PBCD_TYPE = BPB4240_AWA_4240.PBCD_KND;
        BPCSMACD.OUTPUT_FLG = 'Y';
        BPCSMACD.FUNC = 'D';
        S000_CALL_BPZSMACD();
    }
    public void S000_CALL_BPZSMACD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-MAINT-ANALYSIS-CD";
        SCCCALL.COMMAREA_PTR = BPCSMACD;
        SCCCALL.ERR_FLDNO = BPB4240_AWA_4240.TXN_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
