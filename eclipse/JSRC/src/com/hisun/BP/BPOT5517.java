package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5517 {
    char K_ERROR = 'E';
    String PGM_SCSSIEAP = "SCSSIEAP";
    String CPN_F_S_MAIN_FPEN = "BP-F-S-B-Q-FPEN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXIT SCCEXIT = new SCCEXIT();
    BPCFCGRL BPCFCGRL = new BPCFCGRL();
    SCCGWA SCCGWA;
    BPB5517_AWA_5517 BPB5517_AWA_5517;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5517 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5517_AWA_5517>");
        BPB5517_AWA_5517 = (BPB5517_AWA_5517) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.EXT_APP);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.TRM_NO);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.DT);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.AC_NO);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.CI_NO);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.BK_NO);
        CEP.TRC(SCCGWA, BPB5517_AWA_5517.TLR_NO);
        if (BPB5517_AWA_5517.ORG != 0 
            && BPB5517_AWA_5517.OPT != ' ') {
            IBS.init(SCCGWA, BPCFCGRL);
            BPCFCGRL.DATA_BR = BPB5517_AWA_5517.ORG;
            BPCFCGRL.OPT_TYP = BPB5517_AWA_5517.OPT;
            IBS.CALLCPN(SCCGWA, "BP-F-CORG-ATH-CTRL", BPCFCGRL);
            CEP.TRC(SCCGWA, BPCFCGRL.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
