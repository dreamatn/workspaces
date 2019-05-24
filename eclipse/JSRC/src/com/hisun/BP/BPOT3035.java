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

public class BPOT3035 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP260";
    String CPN_S_MAINTN_INVT = "BP-S-MAINTN-INVT    ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSINVT BPCSINVT = new BPCSINVT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOINVO BPCOINVO = new BPCOINVO();
    SCCGWA SCCGWA;
    BPB3035_AWA_3035 BPB3035_AWA_3035;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3035 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3035_AWA_3035>");
        BPB3035_AWA_3035 = (BPB3035_AWA_3035) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSINVT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CHECK_TLR_FOR_CN();
        if (pgmRtn) return;
        B030_RCECORD_LIB_INVT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_01_CHECK_INVNTY();
        if (pgmRtn) return;
        B010_02_CHECK_HANDLER();
        if (pgmRtn) return;
        B010_03_CHECK_JURNNO();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_INVNTY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B01001");
        CEP.TRC(SCCGWA, BPB3035_AWA_3035.INVNTY1);
        CEP.TRC(SCCGWA, BPB3035_AWA_3035.INVNTY2);
