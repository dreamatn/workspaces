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

public class BPZSPROD {
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP001";
    String K_HIS_REMARK = "TS PRODUCT DEFAULT INFORMATION MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCRPROD BPCRPROD = new BPCRPROD();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSPROD BPCSPROD;
    public void MP(SCCGWA SCCGWA, BPCSPROD BPCSPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPROD = BPCSPROD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPROD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B002_MOVE_PARM_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "HEREJIJ1");
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.EFF_DATE);
        if (BPCSPROD.DATA_TXT.EFF_DATE == 0) {
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCPRMM.EFF_DT = BPCSPROD.DATA_TXT.EFF_DATE;
        }
        if (BPCSPROD.DATA_TXT.EXP_DATE == 0) {
            BPCPRMM.EXP_DT = 99991231;
        } else {
            BPCPRMM.EXP_DT = BPCSPROD.DATA_TXT.EXP_DATE;
        }
        CEP.TRC(SCCGWA, BPCSPROD.FUNC);
        if (BPCSPROD.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSPROD.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            W000_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (BPCSPROD.FUNC == 'M') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            W000_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (BPCSPROD.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            W000_HISTORY_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR);
        }
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B002_MOVE_PARM_DATA() throws IOException,SQLException,Exception {
        R000_TRANS_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSPROD.KEY);
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRPRMT.KEY.CD.substring(10 + 10 - 1);
        if (BPCSPROD.DATA_TXT.DESC == null) BPCSPROD.DATA_TXT.DESC = "";
        JIBS_tmp_int = BPCSPROD.DATA_TXT.DESC.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSPROD.DATA_TXT.DESC += " ";
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.DESC.substring(0, 20));
        if (BPCSPROD.DATA_TXT.CDESC == null) BPCSPROD.DATA_TXT.CDESC = "";
        JIBS_tmp_int = BPCSPROD.DATA_TXT.CDESC.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSPROD.DATA_TXT.CDESC += " ";
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.CDESC.substring(0, 60));
        if (BPCSPROD.DATA_TXT.DESC == null) BPCSPROD.DATA_TXT.DESC = "";
        JIBS_tmp_int = BPCSPROD.DATA_TXT.DESC.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSPROD.DATA_TXT.DESC += " ";
        BPRPRMT.DESC = BPCSPROD.DATA_TXT.DESC.substring(0, 20);
        if (BPCSPROD.DATA_TXT.CDESC == null) BPCSPROD.DATA_TXT.CDESC = "";
        JIBS_tmp_int = BPCSPROD.DATA_TXT.CDESC.length();
        for (int i=0;i<60-JIBS_tmp_int;i++) BPCSPROD.DATA_TXT.CDESC += " ";
        BPRPRMT.CDESC = BPCSPROD.DATA_TXT.CDESC.substring(0, 30);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.DESC);
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.CDESC);
        CEP.TRC(SCCGWA, BPRPRMT.DESC);
        CEP.TRC(SCCGWA, BPRPRMT.CDESC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRPROD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '3';
        BPCPRMM.EFF_DT = BPCSPROD.DATA_TXT.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSPROD.DATA_TXT.EXP_DATE;
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.EFF_DATE);
        CEP.TRC(SCCGWA, BPCSPROD.DATA_TXT.EXP_DATE);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCSPROD.DATA_TXT.EFF_DATE = BPCPRMM.EFF_DT;
        BPCSPROD.DATA_TXT.EXP_DATE = BPCPRMM.EXP_DT;
        BPCSPROD.DATA_TXT.DESC = BPRPRMT.DESC;
        BPCSPROD.DATA_TXT.CDESC = BPRPRMT.CDESC;
