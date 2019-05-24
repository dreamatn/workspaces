package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCPRMR;
import com.hisun.SC.SCRPARM;

public class BPZSTSTS {
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "STS AUTH INFO MAINTAIN  ";
    String K_CPY_BPRTSTS = "BPRTSTS ";
    String CPN_R_TSTS_MAINTANCE = "BP-R-TSTS-MAINTAIN  ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_BRW_PC = "BP-R-STARTBR-TSTS   ";
    String K_PARM_TYPE = "PSTS ";
    String K_OUTPUT_FMT = "BP553";
    String K_OUTPUT_SUB_FMT = "BPX01";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    BPZSTSTS_WS_TSTS_HEAD WS_TSTS_HEAD = new BPZSTSTS_WS_TSTS_HEAD();
    BPZSTSTS_WS_TSTS_HEAD_LIST WS_TSTS_HEAD_LIST = new BPZSTSTS_WS_TSTS_HEAD_LIST();
    String WS_ERR_MSG = " ";
    char WS_TBL_TSTS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTSTS BPRTSTS = new BPRTSTS();
    BPRTSTS BPROSTS = new BPRTSTS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMSG BPRMSG = new BPRMSG();
    SCCPRMR SCCPRMR = new SCCPRMR();
    BPCRTSTS BPCRTSTS = new BPCRTSTS();
    BPCRTSTB BPCRTSTB = new BPCRTSTB();
    BPCOTSTB BPCOTSTB = new BPCOTSTB();
    BPCOTSTS BPCOTSTS = new BPCOTSTS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCRMBPM SCCRMBPM = new SCCRMBPM();
    SCRPARM SCRPARM = new SCRPARM();
    SCCPRMM SCCPRMM = new SCCPRMM();
    BPRPTSTS BPRPTSTS = new BPRPTSTS();
    BPRPTSTS BPRTSTSO = new BPRPTSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTSTS BPCSTSTS;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCSTSTS BPCSTSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTSTS = BPCSTSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSTSTS);
        CEP.TRC(SCCGWA, "BPZSTSTS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRTSTS);
        IBS.init(SCCGWA, BPCRTSTS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSTSTS.FUNC == 'A') {
            B010_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTSTS.FUNC == 'U') {
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTSTS.FUNC == 'D') {
            B030_DELETE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTSTS.FUNC == 'Q') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSTSTS.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, BPRPTSTS);
        R000_TRANS_DATA_1();
        if (pgmRtn) return;
        SCCPRMM.FUNC = '0';
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        if (BPCRTSTS.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTSTS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, BPRPTSTS);
        SCCPRMM.FUNC = '4';
        BPRPTSTS.KEY1.TYP = "TSTS ";
        CEP.TRC(SCCGWA, BPCSTSTS.TSTS_APP);
        BPRPTSTS.KEY1.KEY.TSTS_APP = BPCSTSTS.TSTS_APP;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.TSTS_NO = BPCSTSTS.TSTS_NO;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.CHNL = BPCSTSTS.CHNL;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        SCCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPRPTSTS, BPRTSTSO);
        CEP.TRC(SCCGWA, BPRPTSTS);
        IBS.init(SCCGWA, BPRPTSTS);
        R000_TRANS_DATA_1();
        if (pgmRtn) return;
        SCCPRMM.FUNC = '2';
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTSTS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROSTS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRTSTS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, BPRPTSTS);
        SCCPRMM.FUNC = '4';
        BPRPTSTS.KEY1.TYP = "TSTS ";
        BPRPTSTS.KEY1.KEY.TSTS_APP = BPCSTSTS.TSTS_APP;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.TSTS_NO = BPCSTSTS.TSTS_NO;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.CHNL = BPCSTSTS.CHNL;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        SCCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRPTSTS.KEY1.KEY.TSTS_APP);
        CEP.TRC(SCCGWA, BPRPTSTS.KEY1.KEY.TSTS_NO);
        CEP.TRC(SCCGWA, SCCPRMM.EFF_DT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        SCCPRMM.FUNC = '1';
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTSTS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, BPRPTSTS);
        SCCPRMM.FUNC = '3';
        BPRPTSTS.KEY1.TYP = "TSTS ";
        BPRPTSTS.KEY1.KEY.TSTS_APP = BPCSTSTS.TSTS_APP;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.TSTS_NO = BPCSTSTS.TSTS_NO;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.CHNL = BPCSTSTS.CHNL;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        SCCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B050_01_STARTBR_PROC();
        if (pgmRtn) return;
        B050_02_READNEXT_PROC();
        if (pgmRtn) return;
        if (SCCRMBPM.RETURN_INFO == 'F' 
            && BPRPTSTS.KEY1.KEY.TSTS_APP.equalsIgnoreCase(BPCSTSTS.TSTS_APP)) {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCRMBPM.RETURN_INFO != 'L' 
            && BPRPTSTS.KEY1.KEY.TSTS_APP.equalsIgnoreCase(BPCSTSTS.TSTS_APP) 
            && WS_CNT <= 200 
            && SCCMPAG.FUNC != 'E') {
            if (SCCRMBPM.RETURN_INFO == 'F' 
                && BPRPTSTS.KEY1.KEY.TSTS_APP.equalsIgnoreCase(BPCSTSTS.TSTS_APP)) {
                B080_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            B050_02_READNEXT_PROC();
            if (pgmRtn) return;
        }
        B050_03_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B050_01_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        IBS.init(SCCGWA, SCCRMBPM);
        IBS.init(SCCGWA, SCRPARM);
        SCCRMBPM.FUNC = 'S';
        SCRPARM.KEY.TYPE = "TSTS ";
        if (SCRPARM.KEY.CODE == null) SCRPARM.KEY.CODE = "";
        JIBS_tmp_int = SCRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SCRPARM.KEY.CODE += " ";
        if (BPCSTSTS.TSTS_APP == null) BPCSTSTS.TSTS_APP = "";
        JIBS_tmp_int = BPCSTSTS.TSTS_APP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSTSTS.TSTS_APP += " ";
        SCRPARM.KEY.CODE = BPCSTSTS.TSTS_APP + SCRPARM.KEY.CODE.substring(2);
        CEP.TRC(SCCGWA, BPCSTSTS.TSTS_APP);
        CEP.TRC(SCCGWA, SCRPARM.KEY.CODE);
        S000_CALL_SCZRMBPM();
        if (pgmRtn) return;
    }
    public void B050_02_READNEXT_PROC() throws IOException,SQLException,Exception {
        SCCRMBPM.FUNC = 'R';
        S000_CALL_SCZRMBPM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPTSTS);
        BPRPTSTS.KEY1.CD = SCRPARM.KEY.CODE;
        IBS.CPY2CLS(SCCGWA, BPRPTSTS.KEY1.CD, BPRPTSTS.KEY1.KEY);
