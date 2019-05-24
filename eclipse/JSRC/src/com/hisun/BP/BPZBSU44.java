package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZBSU44 {
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRBSP44 BPRBSP44 = new BPRBSP44();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    BPRBSP44 BPRBSPT;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBSU44 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRBSPT = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRBSP44);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSPT);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRBSPT, BPRBSP44);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BSP44-AP_TYPE     ");
        CEP.TRC(SCCGWA, BPRBSP44.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, "BSP44-AP_BATNO    ");
        CEP.TRC(SCCGWA, BPRBSP44.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, "BSP44-BAT_NO      ");
        CEP.TRC(SCCGWA, BPRBSP44.KEY.BAT_NO);
        CEP.TRC(SCCGWA, "BSP44-BK          ");
        CEP.TRC(SCCGWA, BPRBSP44.BK);
        CEP.TRC(SCCGWA, "BSP44-BR          ");
        CEP.TRC(SCCGWA, BPRBSP44.BR);
        CEP.TRC(SCCGWA, "BSP44-REQ_CHNL    ");
        CEP.TRC(SCCGWA, BPRBSP44.REQ_CHNL);
        CEP.TRC(SCCGWA, "BSP44-REQ_SYS     ");
        CEP.TRC(SCCGWA, BPRBSP44.REQ_SYS);
        CEP.TRC(SCCGWA, "BSP44-SYS_DT      ");
        CEP.TRC(SCCGWA, BPRBSP44.SYS_DT);
        CEP.TRC(SCCGWA, "BSP44-SYS_JRN     ");
        CEP.TRC(SCCGWA, BPRBSP44.SYS_JRN);
        CEP.TRC(SCCGWA, "BSP44-AP_REF      ");
        CEP.TRC(SCCGWA, BPRBSP44.AP_REF);
        CEP.TRC(SCCGWA, "BSP44-CLEAR_DATE  ");
        CEP.TRC(SCCGWA, BPRBSP44.CLEAR_DATE);
        CEP.TRC(SCCGWA, "BSP44-TLT         ");
        CEP.TRC(SCCGWA, BPRBSP44.TLT);
        CEP.TRC(SCCGWA, "BSP44-REQ_CHNL2   ");
        CEP.TRC(SCCGWA, BPRBSP44.REQ_CHNL2);
        CEP.TRC(SCCGWA, "BSP44-CHNL_DTL    ");
        CEP.TRC(SCCGWA, BPRBSP44.CHNL_DTL);
        CEP.TRC(SCCGWA, "BSP44-AC_DATE     ");
        CEP.TRC(SCCGWA, BPRBSP44.AC_DATE);
        CEP.TRC(SCCGWA, "BSP44-RUN_TYPE    ");
        CEP.TRC(SCCGWA, BPRBSP44.RUN_TYPE);
        CEP.TRC(SCCGWA, "BSP44-RUN_CMP_NAME");
        CEP.TRC(SCCGWA, BPRBSP44.RUN_CMP_NAME);
        CEP.TRC(SCCGWA, "BSP44-REV_CMP_NAME");
        CEP.TRC(SCCGWA, BPRBSP44.REV_CMP_NAME);
        CEP.TRC(SCCGWA, "BSP44-ERR_ROLL_FLG");
        CEP.TRC(SCCGWA, BPRBSP44.ERR_ROLL_FLG);
        CEP.TRC(SCCGWA, "BSP44-TR_DATA     ");
