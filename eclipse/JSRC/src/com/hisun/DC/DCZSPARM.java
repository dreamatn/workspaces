package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSPARM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "SYSTEM PARAMETER MAINTAIN";
    String K_CPY_DCRCPARM = "DCRCPARM";
    int K_MAX_CNT = 500;
    int K_MAX_COL = 99;
    String K_OUTPUT_FMT_9 = "DC091";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_REC_CNT = 0;
    DCZSPARM_WS_CPARM_KEY WS_CPARM_KEY = new DCZSPARM_WS_CPARM_KEY();
    DCZSPARM_WS_SPARM_HEAD WS_SPARM_HEAD = new DCZSPARM_WS_SPARM_HEAD();
    DCZSPARM_WS_SPARM_DETAIL WS_SPARM_DETAIL = new DCZSPARM_WS_SPARM_DETAIL();
    char WS_END_FLAG = ' ';
    char WS_OUT_FLAG = ' ';
    char WS_FND_TELLER = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMB BPCPRMB = new BPCPRMB();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DCRCPARM DCROPARM = new DCRCPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCPARMO DCCPARMO = new DCCPARMO();
    DCCPARMB DCCPARMB = new DCCPARMB();
    BPRTLT BPRTLT = new BPRTLT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSPARM DCCSPARM;
    public void MP(SCCGWA SCCGWA, DCCSPARM DCCSPARM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSPARM = DCCSPARM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSPARM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSPARM.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPARM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPARM.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPARM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSPARM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_DATA() throws IOException,SQLException,Exception {
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCPARM);
        IBS.init(SCCGWA, BPCPRMR);
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMR.FUNC = ' ';
        WS_CPARM_KEY.WS_CPARM_BANK = DCCSPARM.BNK;
        DCRCPARM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPARM_KEY);
        IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
        BPCPRMR.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
            CEP.TRC(SCCGWA, "1111111111111111111111111111111");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCPARM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EFF_DT = DCCSPARM.EFF_DT;
        BPCPRMM.EXP_DT = DCCSPARM.EXP_DT;
        DCRCPARM.KEY.TYP = "DCPRM";
        CEP.TRC(SCCGWA, DCRCPARM.KEY.TYP);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        DCRCPARM.DATA_LEN = 49;
        BPCPRMM.DAT_PTR = DCRCPARM;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_DCRCPARM;
        BPCPNHIS.INFO.FMT_ID_LEN = 202;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCPARM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PPPPPPPPPPPPPPPPPPPPP");
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCPARM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMM.EFF_DT = DCCSPARM.EFF_DT;
        WS_CPARM_KEY.WS_CPARM_BANK = DCCSPARM.BNK;
        DCRCPARM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPARM_KEY);
        IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
        BPCPRMM.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCPARM, DCROPARM);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMM);
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMM.FUNC = '2';
        BPCPRMM.EFF_DT = DCCSPARM.EFF_DT;
        BPCPRMM.EXP_DT = DCCSPARM.EXP_DT;
        DCRCPARM.DATA_LEN = 49;
        BPCPRMM.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_DCRCPARM;
        BPCPNHIS.INFO.FMT_ID_LEN = 202;
        BPCPNHIS.INFO.OLD_DAT_PT = DCROPARM;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCPARM;
        CEP.TRC(SCCGWA, K_CPY_DCRCPARM);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP);
        CEP.TRC(SCCGWA, DCROPARM);
        CEP.TRC(SCCGWA, DCRCPARM);
        CEP.TRC(SCCGWA, BPCPNHIS);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCPARM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMM.EFF_DT = DCCSPARM.EFF_DT;
        WS_CPARM_KEY.WS_CPARM_BANK = DCCSPARM.BNK;
        DCRCPARM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPARM_KEY);
        IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
        BPCPRMM.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B040_01_HISTORY_RECORD();
        if (pgmRtn) return;
        DCRCPARM.KEY.TYP = "DCPRM";
        BPCPRMM.FUNC = '1';
        DCRCPARM.DATA_LEN = 49;
        BPCPRMM.DAT_PTR = DCRCPARM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_DCRCPARM;
        BPCPNHIS.INFO.FMT_ID_LEN = 202;
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCPARM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "DCPRM";
        BPRPARM.KEY.CODE = DCCSPARM.BNK;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        B051_OUTPUT_TITLE();
        if (pgmRtn) return;
        B052_OUTPUT_HEADLINE();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        WS_REC_CNT = 0;
        for (WS_CNT = 1; BPCRMBPM.RETURN_INFO != 'L' 
            && SCCMPAG.FUNC != 'E' 
            && WS_CNT < K_MAX_CNT; WS_CNT += 1) {
            B053_OUTPUT_DETAIL();
            if (pgmRtn) return;
            WS_REC_CNT += 1;
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B051_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 52;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 8;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B052_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
    }
    public void B053_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SPARM_DETAIL);
        IBS.init(SCCGWA, DCRCPARM);
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, DCRCPARM.DATA_TXT);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        WS_SPARM_DETAIL.WS_SPARM_CODE = BPRPARM.KEY.CODE.substring(0, 3);
        WS_SPARM_DETAIL.WS_SPARM_PSW_LOCK = DCRCPARM.DATA_TXT.PSW_LOCK_NUM;
        WS_SPARM_DETAIL.WS_SPARM_LOS_TMP = DCRCPARM.DATA_TXT.LOS_TMP_DAYS;
        WS_SPARM_DETAIL.WS_SPARM_LOS_DAYS = DCRCPARM.DATA_TXT.LOS_DAYS;
        WS_SPARM_DETAIL.WS_SPARM_PSW_LOS = DCRCPARM.DATA_TXT.PSW_LOS_DAYS;
        WS_SPARM_DETAIL.WS_SPARM_JDL_FRZ = DCRCPARM.DATA_TXT.JDL_FRZ_DAYS;
        WS_SPARM_DETAIL.WS_SPARM_AUTH_AMT = DCRCPARM.DATA_TXT.AUTH_REL_DAYS;
        WS_SPARM_DETAIL.WS_SPARM_AUTH_REL = DCRCPARM.DATA_TXT.AUTH_AMT_SCOPE;
        WS_SPARM_DETAIL.WS_SPARM_DF_AN_FEE = DCRCPARM.DATA_TXT.DF_AN_FEE;
        WS_SPARM_DETAIL.WS_SPARM_DB_FREE = DCRCPARM.DATA_TXT.DB_FREE;
        WS_SPARM_DETAIL.WS_SPARM_EFF_DT = BPRPARM.EFF_DATE;
        WS_SPARM_DETAIL.WS_SPARM_EXP_DT = BPRPARM.EXP_DATE;
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_CODE);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_EFF_DT);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_PSW_LOCK);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_LOS_TMP);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_LOS_DAYS);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_PSW_LOS);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_JDL_FRZ);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_DF_AN_FEE);
        CEP.TRC(SCCGWA, WS_SPARM_DETAIL.WS_SPARM_DB_FREE);
        CEP.TRC(SCCGWA, "============================");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SPARM_DETAIL);
        SCCMPAG.DATA_LEN = 52;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, DCCPARMO);
        SCCFMT.DATA_PTR = DCCPARMO.OUTPUT;
        SCCFMT.DATA_LEN = 52;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, DCCPARMO);
        DCCPARMO.OUTPUT.BNK = DCCSPARM.BNK;
        DCCPARMO.OUTPUT.PSW_LOCK = DCCSPARM.PSW_LOCK;
        DCCPARMO.OUTPUT.LOS_TMP = DCCSPARM.LOS_TMP;
        DCCPARMO.OUTPUT.LOS_DAYS = DCCSPARM.LOS_DAYS;
        DCCPARMO.OUTPUT.PSW_LOS = DCCSPARM.PSW_LOS;
        DCCPARMO.OUTPUT.JDL_FRZ = DCCSPARM.JDL_FRZ;
        DCCPARMO.OUTPUT.AUTH_AMT = DCCSPARM.AUTH_AMT;
        DCCPARMO.OUTPUT.AUTH_REL = DCCSPARM.AUTH_REL;
        DCCPARMO.OUTPUT.DF_AN_FEE = DCCSPARM.DF_AN_FEE;
        DCCPARMO.OUTPUT.DB_FREE = DCCSPARM.DB_FREE;
        DCCPARMO.OUTPUT.EFF_DT = DCCSPARM.EFF_DT;
        DCCPARMO.OUTPUT.EXP_DT = DCCSPARM.EXP_DT;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, DCCPARMO);
        SCCFMT.DATA_PTR = DCCPARMO.OUTPUT;
        SCCFMT.DATA_LEN = 52;
        CEP.TRC(SCCGWA, DCCPARMO.OUTPUT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        WS_CPARM_KEY.WS_CPARM_BANK = DCCSPARM.BNK;
        DCRCPARM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPARM_KEY);
        IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
        DCRCPARM.DATA_TXT.PSW_LOCK_NUM = DCCSPARM.PSW_LOCK;
        DCRCPARM.DATA_TXT.LOS_TMP_DAYS = DCCSPARM.LOS_TMP;
        DCRCPARM.DATA_TXT.LOS_DAYS = DCCSPARM.LOS_DAYS;
        DCRCPARM.DATA_TXT.PSW_LOS_DAYS = DCCSPARM.PSW_LOS;
        DCRCPARM.DATA_TXT.JDL_FRZ_DAYS = DCCSPARM.JDL_FRZ;
        DCRCPARM.DATA_TXT.AUTH_REL_DAYS = DCCSPARM.AUTH_AMT;
        DCRCPARM.DATA_TXT.AUTH_AMT_SCOPE = DCCSPARM.AUTH_REL;
        DCRCPARM.DATA_TXT.DF_AN_FEE = DCCSPARM.DF_AN_FEE;
        DCRCPARM.DATA_TXT.DB_FREE = DCCSPARM.DB_FREE;
        CEP.TRC(SCCGWA, DCRCPARM.KEY.KEY1.BANK);
        CEP.TRC(SCCGWA, DCRCPARM.KEY.CD);
        CEP.TRC(SCCGWA, "===============================");
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPARMO);
        DCCPARMO.OUTPUT.BNK = DCCSPARM.BNK;
        DCCPARMO.OUTPUT.PSW_LOCK = DCCSPARM.PSW_LOCK;
        DCCPARMO.OUTPUT.LOS_TMP = DCCSPARM.LOS_TMP;
        DCCPARMO.OUTPUT.LOS_DAYS = DCCSPARM.LOS_DAYS;
        DCCPARMO.OUTPUT.PSW_LOS = DCCSPARM.PSW_LOS;
        DCCPARMO.OUTPUT.JDL_FRZ = DCCSPARM.JDL_FRZ;
        DCCPARMO.OUTPUT.AUTH_AMT = DCCSPARM.AUTH_AMT;
        DCCPARMO.OUTPUT.AUTH_REL = DCCSPARM.AUTH_REL;
        DCCPARMO.OUTPUT.EFF_DT = DCCSPARM.EFF_DT;
        DCCPARMO.OUTPUT.EXP_DT = DCCSPARM.EXP_DT;
        DCCPARMO.OUTPUT.DF_AN_FEE = DCCSPARM.DF_AN_FEE;
        DCCPARMO.OUTPUT.DB_FREE = DCCSPARM.DB_FREE;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "333333333333333333333333333333333");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "KKKKKKKKKKKKKKKKKKKKKKKK");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
