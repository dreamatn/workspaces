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
import com.hisun.SC.SCCSUBS;

public class BPOT1167 {
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_FCPN_MAINTAIN = "BP-F-S-MAINTAIN-FCPN";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_NEXT = 0;
    char WS_INPUT_ENDED = ' ';
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCPN BPCOFCPN = new BPCOFCPN();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPC1167 BPC1167;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1167 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1167 = new BPC1167();
        IBS.init(SCCGWA, BPC1167);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1167);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_SET_SUBSCODE();
        if (pgmRtn) return;
        B030_QUERY_CPN_FEE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPC1167.FUNC;
        if ((WS_FUNC_FLG != 'U' 
            && WS_FUNC_FLG != 'D' 
            && WS_FUNC_FLG != 'Q')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPC1167.CPNT_ID.trim().length() == 0) {
