package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT2095 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP225";
    String CPN_S_CASAPP_MAINTAIN = "BP-S-CASAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    int K_CNT = 5;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_APP_UPBR = 0;
    int WS_UP_UPBR = 0;
    char WS_APP_LVL = ' ';
    char WS_UP_LVL = ' ';
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSARAP BPCSARAP = new BPCSARAP();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    BPCPRGRL BPCPRGRL = new BPCPRGRL();
    SCCGWA SCCGWA;
    BPB2095_AWA_2095 BPB2095_AWA_2095;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2095 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2095_AWA_2095>");
        BPB2095_AWA_2095 = (BPB2095_AWA_2095) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_CALL_BPZPRGRL();
        if (pgmRtn) return;
    }
    public void B030_CALL_BPZPRGRL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGRL);
