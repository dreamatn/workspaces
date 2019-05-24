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

public class BPZSBNKM {
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "BANK INFOMATION MAINTAIN";
    String K_CPY_BPRPBANK = "BPRPBANK";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    BPZSBNKM_WS_TEMP_BANK WS_TEMP_BANK = new BPZSBNKM_WS_TEMP_BANK();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPBANK BPRPBANK = new BPRPBANK();
    BPRPBANK BPROBANK = new BPRPBANK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOBNKO BPCOBNKO = new BPCOBNKO();
    SCCGWA SCCGWA;
    BPCSBNKM BPCSBNKM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBNKM BPCSBNKM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBNKM = BPCSBNKM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBNKM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOBNKO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBNKM);
        if (BPCSBNKM.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBNKM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBNKM.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSBNKM.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B040_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAAA");
        CEP.TRC(SCCGWA, BPCOBNKO);
        CEP.TRC(SCCGWA, BPCOBNKO.CITY_CD);
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBANK);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCSBNKM.FUNC_CODE == 'A') {
            BPRPBANK.KEY.TYP = "BANK ";
        } else {
            BPRPBANK.KEY.TYP = "BANK ";
            BPCPRMM.FUNC = '3';
            BPRPBANK.KEY.CD = BPCSBNKM.BNK;
            BPCPRMM.EFF_DT = BPCSBNKM.EFF_DT;
