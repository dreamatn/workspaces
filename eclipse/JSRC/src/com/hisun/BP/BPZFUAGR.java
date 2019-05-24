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

public class BPZFUAGR {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_REMARKS = "FEE AGR INFO";
    String K_HIS_COPYBOOK_NAME = "BPCOAGRO";
    String WS_ERR_MSG = " ";
    String WS_FEE_CODE = " ";
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_IDX = 0;
    short WS_NO = 0;
    char WS_REC_DATA_CHG_FLG = ' ';
    char WS_INPUT_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFAGR BPVFAGR = new BPVFAGR();
    BPVFAGR BPVHAGR = new BPVFAGR();
    BPCOAGRO BPCHAGRO = new BPCOAGRO();
    BPCOAGRO BPCHAGRN = new BPCOAGRO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCOFAGR BPCOUAGR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFAGR BPCOUAGR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUAGR = BPCOUAGR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUAGR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPVFAGR);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUAGR.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAGR.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAGR.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAGR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUAGR.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOUAGR.KEY.CHG_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPVFAGR);
